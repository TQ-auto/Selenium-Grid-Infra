## Prerequisites

Install Docker if you don't have it: https://docs.docker.com/desktop/#download-and-install
*It's neccessary to have the tested app up and running before (https://github.com/TQ-auto/testing-app.git)

Create network:
```
docker network create shared-net
```

Selenium-grid containers
Run:
```
docker-compose up -d
```

Jenkins containers
Run:
```
docker-compose -f .\dockers\jenkins-dind\docker-compose.yaml up
```



