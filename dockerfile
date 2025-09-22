FROM public.ecr.aws/docker/library/ubuntu:24.04
RUN echo "image built from repo Dockerfile" > /image-built.txt
