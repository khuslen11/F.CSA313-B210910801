import flaskr.db as module_0


def test_init_db(app_context):
    module_0.init_db()


def test_close_db(app_context):
    module_0.close_db()