package br.com.fiserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiserv.entity.OrderTb;

public interface OrderRepository extends JpaRepository<OrderTb, Long> {

}
