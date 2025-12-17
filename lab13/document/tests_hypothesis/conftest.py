import os
import tempfile
import pytest
from flaskr import create_app
from flaskr.db import init_db

@pytest.fixture(scope="session")
def app():
    db_fd, db_path = tempfile.mkstemp()
    os.close(db_fd)

    app = create_app({
        "TESTING": True,
        "DATABASE": db_path,
    })

    with app.app_context():
        init_db()

    yield app
    os.unlink(db_path)

@pytest.fixture
def client(app):
    return app.test_client()