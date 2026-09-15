$url = "http://localhost/j_spring_security_check"
$numRequests = 100
$concurrency = 50

Write-Host "Starting load test with $numRequests requests..."

# Create a runspace pool for concurrent requests
$RunspacePool = [runspacefactory]::CreateRunspacePool(1, $concurrency)
$RunspacePool.Open()

$Jobs = @()

for ($i = 0; $i -lt $numRequests; $i++) {
    $PowerShell = [powershell]::Create().AddScript({
        param($url, $i)
        try {
            $body = @{ j_username="santoshavinash"; j_password="santoshavinash" }
            $response = Invoke-WebRequest -Uri $url -Method Post -Body $body -MaximumRedirection 0 -ErrorAction Stop -UseBasicParsing
            return $response.StatusCode
        } catch {
            if ($_.Exception.Response) {
                return [int]$_.Exception.Response.StatusCode
            } else {
                return $_.Exception.Message
            }
        }
    }).AddArgument($url).AddArgument($i)
    
    $PowerShell.RunspacePool = $RunspacePool
    $Jobs += [PSCustomObject]@{
        Pipe = $PowerShell
        Result = $PowerShell.BeginInvoke()
    }
}

$results = @{}
$completed = 0

foreach ($Job in $Jobs) {
    $status = $Job.Pipe.EndInvoke($Job.Result)
    $Job.Pipe.Dispose()
    
    if (-not $status) { $status = "Error/Timeout" }
    
    if ($results.ContainsKey($status)) {
        $results[$status]++
    } else {
        $results[$status] = 1
    }
    
    $completed++
    if ($completed % 100 -eq 0) {
        Write-Host "Completed $completed / $numRequests"
    }
}

$RunspacePool.Close()
$RunspacePool.Dispose()

Write-Host "`nLoad test results:"
foreach ($key in $results.Keys) {
    Write-Host "Status $key : $($results[$key])"
}
