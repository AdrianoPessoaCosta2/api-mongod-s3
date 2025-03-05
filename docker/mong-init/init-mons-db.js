db = db.getSiblingDB('mons_db');

db.createUser({
  user: "test",
  pwd: "test123",
  roles: [{ role: "readWrite", db: "mons_db" }]
});

db.createCollection("testCollection");
