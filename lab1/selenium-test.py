import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains

# chrome нээх
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.maximize_window()

# Вэб сайт руу орох
driver.get("https://student.must.edu.mn")

# Нэвтрэх талбаруудыг дуустал хүлээх
wait = WebDriverWait(driver, 10)

username_field = wait.until(EC.presence_of_element_located((By.ID, "username")))
password_field = wait.until(EC.presence_of_element_located((By.ID, "password")))
login_button = wait.until(EC.presence_of_element_located((By.XPATH, '//input[@value="Нэвтрэх"]')))

# Хэрэглэгчийн нэр, нууц үг оруулах
username_field.send_keys("B210910801")
password_field.send_keys("Khuslen11!")

# Нэвтрэх товч дарах
login_button.click()

# Шалгалт – Нэвтрэлт амжилттай эсэх
try:
    # Профайл зураг гарч ирэхийг хүлээх
    profile_pic = wait.until(
        EC.presence_of_element_located(
            (By.XPATH, "//img[contains(@src, 'B210910801')]")
        )
    )


    assert profile_pic.is_displayed(), "Профайл зураг харагдахгүй нэвтрэлт амжилтгүй"
    print("Нэвтрэлт амжилттай!")
except:
    print("Нэвтрэлт амжилтгүй боллоо!")


time.sleep(1)

# Зураг гаргах
actions = ActionChains(driver)
actions.move_by_offset(10, 10).click().perform()

time.sleep(2)
# logout дээр дарах
try:
    logout_link = wait.until(
        EC.element_to_be_clickable((By.LINK_TEXT, "Гарах"))
    )
    logout_link.click()
except:
    pass
time.sleep(2)
# chrome хаах
driver.quit()