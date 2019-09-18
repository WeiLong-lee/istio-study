## istio springboot 测试

#部署

###Step 2: Build Order service using mvn

```bash
$ mvn clean install -DskipTests
```
###Step 3: Building the docker image from project

```bash
$ docker image build -f Dockerfile -t 'saas-order:1.0.0' .
```  
###Step 4: Run the order service

```bash
$ kubectl apply -f order-k8s-config.yaml
$ kubectl apply -f order-istio-config.yaml
```

