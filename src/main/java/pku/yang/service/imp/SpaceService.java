package pku.yang.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.ISpaceDao;
import pku.yang.dao.imp.SpaceDao;
import pku.yang.model.Space;
import pku.yang.service.ISpaceService;


@Service
public class SpaceService implements ISpaceService{
	@Autowired
	private ISpaceDao spaceDao;

	@Override
	public void addSpace(String id, String name, int size ,String root){
			Space space = new Space();
			space.setName(name);
			space.setID(id);
			space.setSize(size);
			space.setRoot(root);
			spaceDao.addSpace(space);
	}
	
	
	@Override
	public String addSpace(String name, int size ,String root){
			Space space = new Space();
			space.setName(name);
			space.setSize(size);
			space.setRoot(root);
			return   spaceDao.addSpace(space);
	}

	@Override
	public void deleteSpace(String spaceID) {
		// TODO Auto-generated method stub
		spaceDao.deleteSpaceByID(spaceID);
	}

	@Override
	public List<Space> getSpaceList() {
		// TODO Auto-generated method stub
		List<Space> spacelist = spaceDao.getSpaceList("from Space");
		return spacelist;
	}


	@Override
	public Space findById(String id) {
		// TODO Auto-generated method stub
		return spaceDao.getSpaceByID(id);
	}


	@Override
	public Space findByRootId(String id) {
		// TODO Auto-generated method stub
		return spaceDao.getSpaceByRoot(id);
	}


	
}
