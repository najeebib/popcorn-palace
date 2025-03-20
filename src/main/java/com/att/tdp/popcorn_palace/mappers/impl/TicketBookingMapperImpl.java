package com.att.tdp.popcorn_palace.mappers.impl;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketBookingMapperImpl implements Mapper<TicketBookingEntity, TicketBookingDto> {

    private ModelMapper modelMapper;

    public TicketBookingMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketBookingDto mapTo(TicketBookingEntity ticketBookingEntity) {
        return  modelMapper.map(ticketBookingEntity, TicketBookingDto.class);
    }

    @Override
    public TicketBookingEntity mapFrom(TicketBookingDto ticketBookingDto) {
        return  modelMapper.map(ticketBookingDto, TicketBookingEntity.class);
    }
}
