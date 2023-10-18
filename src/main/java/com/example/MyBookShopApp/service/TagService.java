package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.other.TagEntity;
import com.example.MyBookShopApp.model.TagModel;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import com.example.MyBookShopApp.repository.TagTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TagService {
    private TagRepository tagRepository;
    private TagTypeRepository tagTypeRepository;
    private BookRepository bookRepository;
    @Autowired
    public TagService(TagRepository tagRepository, TagTypeRepository tagTypeRepository, BookRepository bookRepository) {
        this.tagRepository = tagRepository;
        this.tagTypeRepository = tagTypeRepository;
        this.bookRepository = bookRepository;

    }
    public TagEntity getById(Integer id)
    {
        return tagRepository.findById(id).get();
    }
    public List<TagEntity> getAll(){
        return tagRepository.findAll();
    }

    public void refreshAllTag() {
        // удаление всех тегов
        // запись слов названия  и описания книги
        // рассчет количества
        // запись веса в бд

        tagRepository.deleteAll();
        List<String> words = new ArrayList<>();
        Map<String, Long> frequency = new HashMap<>();
        bookRepository.findAll().forEach( book ->

                {
                    for (String s : (book.getDescription() + " "+ book.getTitle()).split(" ")){
                        if (s.length()>4) {
                            words.add(s.replaceAll("[^a-zA-Zа-яёА-ЯЁ]", "").toLowerCase());
                            System.out.print(s + " ");
                        }
                }
                    Map<String, Long> dupMap = countDuplicates(words);
                    for (Map.Entry<String, Long> entry : dupMap.entrySet()) {
                        String key = entry.getKey();
                        Long value = entry.getValue();
                        frequency.merge(key, value, Long::sum);
                    }

                }

        );
        Integer max = frequency.values().stream().max(Long::compare).get().intValue();
        Integer part =  (max/6)+1;
        for (Map.Entry<String, Long> entry : frequency.entrySet()
             ) {
            TagEntity tag = new TagEntity();
            tag.setName(entry.getKey());
            tag.setUsability(entry.getValue());
            Integer type = tag.getUsability().intValue()/part ;
            tag.setType(tagTypeRepository.findById(type).get());
            tagRepository.save(tag);
        }

    }

    public Map<String, Long> countDuplicates(List<String> inputList) {
        return inputList.stream().collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));
    }

    public List<TagModel> getFirst(Integer count){

        List<TagEntity> tagEntities = tagRepository.findAll().stream()
                .filter(
                        tag->(tag.getType().getId()>1)).limit(count).toList();
        return tagEntities.stream().map(TagModel::toModel).toList();
    }
}
