package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;
import com.example.bookreservationhibercompiler.entity.AbstractEntity;
import com.example.bookreservationhibercompiler.mapper.AbstractMapper;
import com.example.bookreservationhibercompiler.service.CommonService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AbstractHibernateService<Entity extends AbstractEntity, DTO extends AbstractDTO>
	extends AbstractMapper<Entity, DTO>
	implements CommonService<Entity, DTO> {

	private Class<Entity> clazzEntity;
	private Class<DTO> clazzDTO;

	@Autowired(required = false)
	SessionFactory sessionFactory;

	public AbstractHibernateService(Class<Entity> clazzEntity, Class<DTO> clazzDTO) {
		super(clazzEntity, clazzDTO);
		this.clazzEntity = clazzEntity;
		this.clazzDTO = clazzDTO;
	}

	@Transactional(readOnly = true)
	public DTO findOne(long id) {
		return toDTO((Entity) getCurrentSession().get(clazzEntity, id));
	}

	@Transactional(readOnly = true)
	public List findAll() {
		return (List) toDTOs(
			getCurrentSession().createQuery("from " + clazzEntity.getName()).list());
	}

	@Transactional
	public DTO create(DTO dto) {
		Entity entity = toEntity(dto);
		getCurrentSession().saveOrUpdate(entity);
		return toDTO(entity);
	}

	@Transactional
	public DTO update(Long id, DTO dto) {
		Entity entity = sessionFactory.getCurrentSession().get(clazzEntity, id);
		return toDTO((Entity) getCurrentSession().merge(entity));
	}

	@Transactional
	public void delete(Entity entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(long id) {
		Entity entity = sessionFactory.getCurrentSession().get(clazzEntity, id);
		;
		delete(entity);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}