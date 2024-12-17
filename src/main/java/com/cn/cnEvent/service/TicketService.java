package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.TicketDAL;
import com.cn.cnEvent.entity.Person;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketDAL ticketDAL;

    @Transactional
    public Ticket getById(Long id) {
        Ticket ticket = ticketDAL.getById(id);
        if(ticket == null){
            throw  new NotFoundException("ticket with given id not founded");
        }
        return ticket;
    }

    @Transactional
    public List<Ticket> getAllTickets() {

        List<Ticket> ticketList = ticketDAL.getAll();
//        if (ObjectUtils.isEmpty(ticketList)){
//            throw new NotFoundException("No person details are Available right now.");
//        }
       return ticketList ;
    }

    @Transactional
    public List<Ticket> getAllTicketsByAge(Long age) {
        List<Ticket> ticketList =ticketDAL.getAllTicketsByAge(age);
        if (ObjectUtils.isEmpty(ticketList)){
            throw new NotFoundException("No person details are Available right now.");
        }
        return ticketList;
    }
}
