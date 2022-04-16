#途中です
#bash:
#	docker run \
#	  --rm \
#	  --interactive \
#	  --tty \
#	  --workdir="/usr/local/app/" \
#	  --user="root" \
#	  --mount type=bind,src="${PWD}/",target="/usr/local/app/" \
#	  eclipse-temurin:17-jdk-focal bash
#
#build-builder:
#	docker build --target builder --tag jdbc_template_example:builder .
#
#bash-builder:
#	docker run --rm -it jdbc_template_example:builder bash
#
#.PHONY: build
#build:
#	docker build --tag jdbc_template_example:latest .
#
#bash:
#	docker run --rm -it jdbc_template_example:latest bash
#
