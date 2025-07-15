# 🌾 Krushistore UI Automation Framework

This is a Selenium TestNG automation framework developed to automate UI testing for [Krushistore](https://www.krushistore.com), an e-commerce platform for agricultural products.

---

## 🧰 Tech Stack

| Tool/Library         | Purpose                                      |
|----------------------|----------------------------------------------|
| Java                 | Core programming language                    |
| Selenium WebDriver   | Browser automation                           |
| TestNG               | Test framework and assertions                |
| Maven                | Project and dependency management            |
| WebDriverManager     | Driver binary management                     |
| ExtentReports (optional) | Reporting (can be integrated)            |


## 📁 Project Structure

krushistore-automation/
├── src/
│ ├── test/
│ │ └── java/
│ │ └── UI_Use_Only_Selenium_TestNg/
│ │ └── A_signIn.java
├── pom.xml
└── README.md

yaml
Copy
Edit

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/krushistore-automation.git
cd krushistore-automation
2. Install Dependencies
Make sure you have Maven installed. Then run:

bash
Copy
Edit
mvn clean install
3. Run Test
bash
Copy
Edit
mvn test
🧪 Sample Test Class: A_signIn.java
Automates the login icon click:

java
Copy
Edit
driver.get("https://www.krushistore.com");
driver.findElement(By.xpath("//*[contains(@class,'profileDropdown')]")).click();

✅ Prerequisites
Java 11 or above

Maven 3.x

IntelliJ IDEA / Eclipse (recommended)

Chrome / Edge / Firefox installed

🔧 Future Enhancements
Page Object Model (POM) structure

Parallel test execution with TestNG XML

Integration with Jenkins for CI

Advanced reports with ExtentReports or Allure

Login automation with valid credentials

🤝 Contributing
If you want to contribute, feel free to fork this repository and create a pull request. Suggestions, improvements, and bug fixes are welcome.

📄 License
This project is licensed under the MIT License.

👨‍💻 Author
Sujit Manmode
Automation Developer | 3+ years experience

