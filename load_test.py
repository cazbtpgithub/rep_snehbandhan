import requests
import concurrent.futures
import time

URL = "http://localhost/j_spring_security_check"
NUM_REQUESTS = 100
CONCURRENCY = 50

def make_request(i):
    data = {
        "j_username": "santoshavinash",
        "j_password": "santoshavinash"
    }
    try:
        # Use a short timeout so we don't hang forever if the pool exhausts
        response = requests.post(URL, data=data, timeout=5, allow_redirects=False)
        return response.status_code
    except Exception as e:
        return str(e)

if __name__ == "__main__":
    start_time = time.time()
    print(f"Starting load test with {NUM_REQUESTS} requests at concurrency {CONCURRENCY}...")
    
    status_counts = {}
    
    with concurrent.futures.ThreadPoolExecutor(max_workers=CONCURRENCY) as executor:
        futures = [executor.submit(make_request, i) for i in range(NUM_REQUESTS)]
        
        for i, future in enumerate(concurrent.futures.as_completed(futures)):
            result = future.result()
            status_counts[result] = status_counts.get(result, 0) + 1
            
            if (i + 1) % 100 == 0:
                print(f"Completed {i + 1}/{NUM_REQUESTS} requests...")
                
    end_time = time.time()
    print(f"\nLoad test finished in {end_time - start_time:.2f} seconds.")
    print("Results:")
    for status, count in status_counts.items():
        print(f"Status {status}: {count}")
