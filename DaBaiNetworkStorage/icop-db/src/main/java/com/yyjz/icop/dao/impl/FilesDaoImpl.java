package com.yyjz.icop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

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

	@Override
	public List<Document> queryAllUserFile(Document queryDat) {
		List<Document> list = new ArrayList<Document>();
		MongoCollection<Document> docs = mdb.getCollection("dbFile");
		FindIterable<Document> find = docs.find(queryDat);
		for (Document document : find) {
			list.add(document);
		}
		return list;
	}
}
