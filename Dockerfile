FROM python:3.9-slim-buster
LABEL authors="maxtorm"
WORKDIR /app
COPY ./app.py ./requirements.txt ./gunicorn.conf.py /app/
RUN pip install -r requirements.txt
EXPOSE 9095
CMD ["gunicorn", "app:app", "-c", "./gunicorn.conf.py"]