package kr.co.hanbit.service;

import kr.co.hanbit.dto.ProductDto;
import kr.co.hanbit.model.Product;
import kr.co.hanbit.repository.ListProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {
    private final ListProductRepository listProductRepository;
    private final ModelMapper modelMapper;
    @Autowired
    SimpleProductService(ListProductRepository listProductRepository, ModelMapper modelMapper) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductDto> findAll() {
        List<Product> products = listProductRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }
    public ProductDto add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = listProductRepository.add(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    public ProductDto findById(Long id) {
        Product product = listProductRepository.findById(id);
        return modelMapper.map(product, ProductDto.class);
    }
    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }
    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updateProduct = listProductRepository.update(product);
        return modelMapper.map(updateProduct, ProductDto.class);
    }
    public void delete(Long id) {
        listProductRepository.delete(id);
    }
}
