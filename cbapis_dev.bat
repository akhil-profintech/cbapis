//./gradlew build ====>alternative command
call ./gradlew bootJar

call ./gradlew bootBuildImage

call docker build --build-arg JAR_FILE=build/libs/cbapis-1.1.jar -t cbapis-dev .

call aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 220474796007.dkr.ecr.ap-south-1.amazonaws.com

call kubectl delete deployment cbapis-dev -n cbapis-dev

call kubectl delete service cbapis-service-dev -n cbapis-dev

call kubectl delete service cbapis-loadbalancer-dev -n cbapis-dev

call docker tag cbapis-dev:latest 220474796007.dkr.ecr.ap-south-1.amazonaws.com/cbapis-dev:latest

call docker push 220474796007.dkr.ecr.ap-south-1.amazonaws.com/cbapis-dev:latest

call aws eks --region ap-south-1 update-kubeconfig --name ProCluster

call kubectl config get-contexts

kubectl create ns cbapis-dev

call kubectl apply -f .\deployment_dev.yaml -n cbapis-dev

call kubectl apply -f .\service_dev.yaml -n cbapis-dev

call kubectl apply -f .\loadbalancer_dev.yaml -n cbapis-dev

call kubectl get deployments -n cbapis-dev

call kubectl get services -n cbapis-dev

call kubectl get pods -n cbapis-dev


call kubectl get service cbapis-loadbalancer-dev -n cbapis-dev  -o jsonpath='{.spec.clusterIP}'
