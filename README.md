# Run experiments

docker build -t experiments .
docker run -it -v ~/Documents/tesis/propertie/experimentos.txt:/app/experimentos.txt experiments bash -c "./gradlew build -x :compileTestJava -x :test && python3 script_config.py>/app/experimentos.txt"