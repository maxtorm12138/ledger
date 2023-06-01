FROM python:3.9-buster
LABEL authors="maxtorm"
ADD . /app
WORKDIR /app
EXPOSE 8080
RUN pip install -r requirements.txt
CMD ["gunicorn", "app:app", "-c", "./gunicorn.conf.py"]