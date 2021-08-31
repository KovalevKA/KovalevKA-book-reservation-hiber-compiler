package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;
import com.example.bookreservationhibercompiler.entity.AbstractEntity;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.service.CommonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

public class AbstractHibernateService<Entity extends AbstractEntity, DTO extends AbstractDTO>
		implements CommonService<Entity, DTO>, CommonMapper<Entity, DTO> {

	private Class<Entity> clazzEntity;
	private Class<DTO> clazzDTO;

	@Autowired(required = false)
	SessionFactory sessionFactory;

	public AbstractHibernateService(Class<Entity> clazzEntity, Class<DTO> clazzDTO) {
		this.clazzEntity = clazzEntity;
		this.clazzDTO = clazzDTO;
	}

	@Transactional(readOnly = true)
	public DTO findOne(long id) {
		return toDTO((Entity) getCurrentSession().get(clazzEntity, id));
	}

	@Transactional(readOnly = true)
	public List<DTO> findAll() {
		return (List<DTO>) toDTOs(
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
		Entity saveEntity = sessionFactory.getCurrentSession().get(clazzEntity, id);

		for (Field field : dto.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Field saveField;
			try {
				saveField = saveEntity.getClass().getDeclaredField(field.getName());
				saveField.setAccessible(true);
				saveField.set(saveEntity, field.get(dto));
				saveField.setAccessible(false);
			} catch (Exception e) {
				throw new IllegalArgumentException("Field not found");
			}
			field.setAccessible(false);
		}
		return toDTO((Entity) getCurrentSession().merge(saveEntity));
	}

	@Transactional
	public void delete(Entity entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(long id) {
		Entity entity = sessionFactory.getCurrentSession().get(clazzEntity, id);
		delete(entity);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
