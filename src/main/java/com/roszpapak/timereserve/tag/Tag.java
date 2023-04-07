package com.roszpapak.timereserve.tag;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.roszpapak.timereserve.business.Business;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties("businesses")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
    @ManyToMany(mappedBy = "tags")
    private Set<Business> businesses;


    public Tag(String value, Set<Business> businesses) {
        this.value = value;
        this.businesses = businesses;
    }
}
