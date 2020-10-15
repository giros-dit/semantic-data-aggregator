import connexion

app = connexion.FlaskApp(__name__, specification_dir='spec/')
app.add_api('entities-resolved.yml')
app.run(port=8080)


