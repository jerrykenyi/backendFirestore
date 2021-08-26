package com.example.demospringfirestore.service.impl;

import com.example.demospringfirestore.commons.GenericServiceImpl;
import com.example.demospringfirestore.dto.ProductoDTO;
import com.example.demospringfirestore.model.Producto;
import com.example.demospringfirestore.service.api.ProductoServiceAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, ProductoDTO> implements ProductoServiceAPI {


    @Autowired
    private Firestore firestore;
    @Override
    public CollectionReference getCollection() {
        return firestore.collection("producto");
    }
}
