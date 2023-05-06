# spring-in-action-six
Estudio sobre el libro de spring in action.
et up the repository
1. Update the apt package index and install packages to allow apt to use a repository over HTTPS:

$ sudo apt-get update
$ sudo apt-get install \
    ca-certificates \
    curl \
    gnupg

2. Add Docker’s official GPG key:
    
$ sudo install -m 0755 -d /etc/apt/keyrings
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
$ sudo chmod a+r /etc/apt/keyrings/docker.gpg

3. Use the following command to set up the repository:
$  echo \
  "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
  

Install Docker Engine
Update the apt package index:

$ sudo apt-get update

2.Install Docker Engine, containerd, and Docker Compose. To install the latest version, run:

$ sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

3. Verify that the Docker Engine installation is successful by running the hello-world image:
sudo docker run hello-world

4. Add your user to the docker group.
sudo usermod -aG docker ${USER}


isntalar docker-compose
To download and install Compose standalone, run: 
curl -SL https://github.com/docker/compose/releases/download/v2.17.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose
docker-compose --version

Apply executable permissions to the standalone binary in the target path for the installation.
Test and execute compose commands using docker-compose.

https://www.educative.io/blog/docker-compose-tutorial
https://docs.docker.com/compose/install/other/

https://www.digitalocean.com/community/questions/how-to-fix-docker-got-permission-denied-while-trying-to-connect-to-the-docker-daemon-socket

Revision de errores comunes
https://www.baeldung.com/linux/docker-permission-denied-daemon-socket-error

