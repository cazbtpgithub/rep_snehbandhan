import os
import re

directory = "src/main/java/com/xanadutec/coreflex"

def refactor_file(path):
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()

    original_content = content

    # 1. Handle the 9 try-with-resources occurrences in LoginDaoImpl
    content = re.sub(
        r'try\s*\(\s*Session\s+session\s*=\s*sessionFactory\.openSession\(\)\s*\)\s*\{',
        r'{ Session session = sessionFactory.getCurrentSession();',
        content
    )

    # 2. Replace openSession() with getCurrentSession()
    content = content.replace("sessionFactory.openSession()", "sessionFactory.getCurrentSession()")

    # 3. Remove session.close();
    content = re.sub(r'session\.close\(\)\s*;\s*', '', content)

    # 4. Remove session.beginTransaction() variants
    content = re.sub(
        r'(org\.hibernate\.)?Transaction\s+(tx|transaction|t)\s*=\s*session\.beginTransaction\(\)\s*;\s*',
        '',
        content
    )

    # 5. Remove tx.commit() variants
    content = re.sub(r'(tx|transaction|t)\.commit\(\)\s*;\s*', '', content)

    # 6. Add @Transactional to the class if not present and sessionFactory is used
    if "sessionFactory.getCurrentSession()" in content:
        if not re.search(r'@Transactional\s+public\s+class', content):
            content = re.sub(r'(public\s+class\s+\w+DaoImpl)', r'@Transactional\n\1', content)
            
        if "import org.springframework.transaction.annotation.Transactional;" not in content:
            # find first import and insert after it
            content = re.sub(
                r'(import .*?;)', 
                r'\1\nimport org.springframework.transaction.annotation.Transactional;', 
                content, 
                count=1
            )

    if content != original_content:
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Refactored {path}")

for root, _, files in os.walk(directory):
    for file in files:
        if file.endswith("DaoImpl.java"):
            path = os.path.join(root, file)
            refactor_file(path)

print("Refactoring complete.")
