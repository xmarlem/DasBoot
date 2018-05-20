package com.boot.dasboot.repository;

import com.boot.dasboot.model.Shipwreck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipWreckRepository extends JpaRepository<Shipwreck, Long> {
}
