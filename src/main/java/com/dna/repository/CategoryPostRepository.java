/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository;

import com.dna.pojos.CategoryPost;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface CategoryPostRepository {
    List<CategoryPost> getCategories();
    
}
