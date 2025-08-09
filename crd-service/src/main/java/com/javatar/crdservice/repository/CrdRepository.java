package com.javatar.crdservice.repository;

import com.javatar.crdservice.model.Crd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrdRepository extends JpaRepository<Crd, String> {
}
