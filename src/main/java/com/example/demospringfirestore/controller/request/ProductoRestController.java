package com.example.demospringfirestore.controller.request;



import com.example.demospringfirestore.controller.response.ResponseMessage;
import com.example.demospringfirestore.dto.ProductoDTO;
import com.example.demospringfirestore.model.Producto;
import com.example.demospringfirestore.service.api.ProductoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/producto/")
@CrossOrigin("*")
public class ProductoRestController {

    @Autowired
    private ProductoServiceAPI productoServiceAPI;

    @GetMapping(value = "/listarProducto")
    public List<ProductoDTO> getAll() throws Exception {
        return productoServiceAPI.getAll();
    }

    @GetMapping(value = "/listarProductoById/{id}")
    public ProductoDTO find(@PathVariable String id) throws Exception {
        return productoServiceAPI.get(id);
    }

    @PostMapping(value = "/guardarProducto/{id}")
    public ResponseEntity<String> save(@RequestBody Producto producto,@PathVariable String id) throws Exception {

        if (id == null || id.length() == 0 || id.equals("null")) {
            if (producto.getPrecio()>0)
            {
                productoServiceAPI.save(producto);
                return new ResponseEntity<String>( HttpStatus.OK);
            }else
            {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        } else {
            productoServiceAPI.save(producto, id);
            return new ResponseEntity<String>(id, HttpStatus.OK);
        }

    }
    @PutMapping(value="/actualizarProducto/{id}")
    public ResponseEntity<String> updateProducto(@RequestBody Producto producto,@PathVariable String id) throws Exception {
          productoServiceAPI.save(producto, id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/eliminarProducto/{id}")
    public ResponseEntity<ProductoDTO> delete(@PathVariable String id) throws Exception {
        ProductoDTO producto = productoServiceAPI.get(id);
        if (producto != null) {
            productoServiceAPI.delete(id);
        } else {
            return new ResponseEntity<ProductoDTO>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<ProductoDTO>(producto, HttpStatus.OK);
    }
}
