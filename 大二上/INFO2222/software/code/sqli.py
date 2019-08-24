import sqlite3

input = raw_input('Enter the name \'Sam\': ')
conn = sqlite3.connect("secretdata.db")
cursor = conn.cursor()
cursor.execute("SELECT data FROM secrets WHERE name = '%s';" % input)
data = cursor.fetchall()

print(data)
