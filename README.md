<img width="1024" height="1024" alt="2f913220-3882-457f-b8ed-932870a07b13" src="https://github.com/user-attachments/assets/267fb784-f363-44c5-9bb9-23dd8370608c" />
📚 Pronote Notifier

Get instant Telegram notifications when new grades are published on Pronote.
This project combines Python (for Pronote access) and Java (for notifications and orchestration).

🚀 Features

Connects securely to your Pronote account using pronotepy
.

Periodically checks for new grades.

Sends instant Telegram messages to your account via a bot.

Uses .env for safe storage of credentials and API keys.

📦 Requirements

Python 3.9+

Java 17+

Maven (for Java dependencies)

Docker (optional) if you want to containerize everything

⚙️ Setup
1. Clone the repository
git clone https://github.com/your-username/pronote-notifier.git
cd pronote-notifier

2. Environment variables

BOT_KEY=your_telegram_bot_token
CHAT_ID=your_chat_id
STORAGE_PATH=file_with_retrieved_grades

3. Python side (Pronote API)

Create a .env file in the resources directory with:

USERNAME=your_username
PASSWORD=your_password
PRONOTE_URL=https://your-pronote-url.fr/pronote/eleve.html

Install dependencies:

cd python-service
pip install -r requirements.txt


Run the Pronote API service (Flask):

python app.py


This will expose an API on:

http://localhost:5000/grades


Example response:

{
  "new_grades": [
    {
      "subject": "Math",
      "grade": "17/20",
      "date": "2025-09-01"
    }
  ]
}

4. Java side (Telegram bot)

Build with Maven:

cd java-service
mvn clean package


Run:

java -jar target/pronote-notifier-1.0-SNAPSHOT.jar


The Java service will:

Call the Python API (/grades)

Compare results with previous checks

Send new grades to Telegram via the bot

📲 Usage Flow

Python service logs into Pronote and exposes an endpoint with grades.

Java service queries this endpoint.

When a new grade appears → Java sends a Telegram notification.

🐳 Docker (optional)

If you prefer running everything with Docker:

docker-compose up --build


This will start both:

python-service (Flask + pronotepy)

java-service (Telegram bot orchestrator)

⚠️ Disclaimer

This project is unofficial and not affiliated with Pronote or EduConnect.
It is for educational purposes only. Please use responsibly.
