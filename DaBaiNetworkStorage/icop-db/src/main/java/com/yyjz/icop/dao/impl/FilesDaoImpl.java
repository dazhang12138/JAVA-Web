package com.yyjz.icop.dao.impl;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yyjz.icop.dao.FilesDao;
import com.yyjz.icop.util.MongoDBUtil;

public class FilesDaoImpl implements FilesDao {

	MongoDatabase mdb = MongoDBUtil.instance.getMdb();
	
	@Override
	public void saveFiles(Document fileDoc){
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		docs.insertOne(fileDoc);
	}

	@Override
	public void saveFile(Document fileDoc) {
		MongoCollection<Document> docs = mdb.getCollection("dbFile");
		docs.insertOne(fileDoc);
	}

	@Override
	public Document queryFiles(Document doc, boolean b) {
		Document d = null;
		MongoCollection<Document> docs;
		if(b){
			docs = mdb.getCollection("dbFiles");
		}else{
			docs = mdb.getCollection("dbFile");
		}
		FindIterable<Document> iterable = docs.find(doc);
		for (Document document2 : iterable) {
			d = document2;
		}
		return d;
	}

	@Override
	public void deleteFiles(Document document) {
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		docs.deleteOne(document);
	}

	@Override
	public void deleteFile(Document document) {
		MongoCollection<Document> docs = mdb.getCollection("dbFile");
		docs.deleteOne(document);
	}
}
