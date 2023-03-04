package com.back.back.repository;
import com.back.back.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad,Long> {
}
