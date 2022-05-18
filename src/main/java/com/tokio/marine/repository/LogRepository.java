package com.tokio.marine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokio.marine.model.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

}
