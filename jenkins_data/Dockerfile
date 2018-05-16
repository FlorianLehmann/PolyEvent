FROM jenkinsci/blueocean
USER root
RUN apk --no-cache add shadow && \
usermod -aG docker jenkins && \
usermod -aG root jenkins && \
usermod -aG sys jenkins && \
usermod -aG daemon jenkins && \
usermod -aG bin jenkins
RUN echo "http://dl-4.alpinelinux.org/alpine/edge/main" >> /etc/apk/repositories && \
    echo "http://dl-4.alpinelinux.org/alpine/edge/community" >> /etc/apk/repositories && \
    echo "http://dl-4.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories && \
    apk add --no-cache \
      bash \
      curl \
      make \
      mono \
      mono-dev \
      mono-lang \
      maven \
      && \
    adduser -D user
USER jenkins
