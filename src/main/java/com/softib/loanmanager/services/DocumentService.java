package com.softib.loanmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softib.loanmanager.entity.Document;
import com.softib.loanmanager.repository.DocumentRepository;

@Service
public class DocumentService {
	@Autowired 
	DocumentRepository documentRepository;

	public Document addDocuments(Document document) {	
		return documentRepository.save(document);
	}

	
	public String checkWeightedDocument(List<Document> documentList){
		Integer allWeigthed=0;
		String response="";
		if(documentList != null){
			
			for (Document document:documentList){
				if(document != null && document.getWeighted() != null){
					allWeigthed= allWeigthed+ document.getWeighted();

				}
			}
			if(allWeigthed >=85){
				response="This loan can be validate he has more than 85 or equal as weight that mean that most of documents are present:validate";
			}
			else {
				response="Unfortunately this loan can not be validate at this moment please check your documents:not Validate";
			}
		}
		return response;
	}
	
	public Document updateOneDocument (Document docToUpdate , Document newDoc){
		if (docToUpdate != null){
			docToUpdate.setDescription(newDoc.getDescription());
			docToUpdate.setName(newDoc.getName());
			documentRepository.save(docToUpdate);
			return docToUpdate;
		}
		return null;
	}
}
