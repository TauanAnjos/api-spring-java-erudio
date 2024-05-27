package br.com.erudio.services;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.handler.RequiredObjetIsNullException;
import br.com.erudio.exceptions.handler.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.BookModel;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.logging.Logger;
@Service
public class BookService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    BookRepository bookRepository;


    public BookVO findById(Long id){
        logger.info("Finding one book!");
        var entity = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }
    public List<BookVO> findAll(){
        logger.info("Finding all books!");
        var books = DozerMapper.parseListObject(bookRepository.findAll(), BookVO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }
    public BookVO create(BookVO book){
        if (book == null)throw  new RequiredObjetIsNullException();
        logger.info("Creating one book!");
        var entity = DozerMapper.parseObject(book, BookModel.class);
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book){
        if (book == null)throw  new RequiredObjetIsNullException();
        logger.info("Update book!");
        var entity = bookRepository.findById(book.getKey()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunch_date(book.getLaunch_date());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id){
        var entity = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }
}
