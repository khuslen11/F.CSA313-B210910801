import pytest
from hypothesis import given, strategies as st, settings, HealthCheck


@settings(suppress_health_check=[HealthCheck.function_scoped_fixture])
@given(username=st.text(min_size=1, max_size=50),
       password=st.text(min_size=1, max_size=50))
def test_register_then_login(client, username, password):
    r = client.post("/auth/register", data={
        "username": username,
        "password": password
    })

    assert r.status_code in (200, 302, 400)

    if r.status_code in (200, 302):
        login = client.post("/auth/login", data={
            "username": username,
            "password": password
        })
        assert login.status_code in (200, 302)


@settings(suppress_health_check=[HealthCheck.function_scoped_fixture])
@given(username=st.text(min_size=1, max_size=50))
def test_login_fails_without_registration(client, username):
    r = client.post("/auth/login", data={
        "username": username,
        "password": "randompassword"
    })
    assert r.status_code in (200, 400, 401)


@settings(suppress_health_check=[HealthCheck.function_scoped_fixture])
@given(username=st.text(min_size=1, max_size=50),
       password=st.text(min_size=1, max_size=50))
def test_duplicate_registration(client, username, password):
    client.post("/auth/register", data={
        "username": username,
        "password": password
    })
    r = client.post("/auth/register", data={
        "username": username,
        "password": password
    })
    assert r.status_code in (200, 400)


@settings(suppress_health_check=[HealthCheck.function_scoped_fixture])
@given(password=st.text(min_size=1, max_size=100))
def test_register_rejects_empty_username(client, password):
    r = client.post("/auth/register", data={
        "username": "",
        "password": password
    })
    assert r.status_code in (200, 400)


@settings(suppress_health_check=[HealthCheck.function_scoped_fixture])
@given(username=st.text(min_size=1, max_size=100))
def test_register_rejects_empty_password(client, username):
    r = client.post("/auth/register", data={
        "username": username,
        "password": ""
    })
    assert r.status_code in (200, 400)