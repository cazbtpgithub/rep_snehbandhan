from html.parser import HTMLParser

class MyHTMLParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.tags = []
        self.void_elements = {'area', 'base', 'br', 'col', 'embed', 'hr', 'img', 'input', 'link', 'meta', 'param', 'source', 'track', 'wbr'}

    def handle_starttag(self, tag, attrs):
        if tag not in self.void_elements:
            self.tags.append((tag, self.getpos()))

    def handle_endtag(self, tag):
        if tag not in self.void_elements:
            if not self.tags:
                print(f"Error: Closing tag </{tag}> at {self.getpos()} has no matching start tag.")
                return
            last_tag, pos = self.tags.pop()
            if last_tag != tag:
                print(f"Error: Mismatched tags. Expected </{last_tag}> for tag started at {pos}, but found </{tag}> at {self.getpos()}")

parser = MyHTMLParser()
with open('f:/Antigravity/Sneh_Bandhan/src/main/webapp/WEB-INF/views/UserHome.jsp', 'r', encoding='ISO-8859-1') as f:
    parser.feed(f.read())

if parser.tags:
    print("Unclosed tags remaining:")
    for tag, pos in parser.tags:
        print(f"<{tag}> at {pos}")
