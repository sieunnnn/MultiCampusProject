package multi.second.project.group;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import multi.second.project.domain.group.domain.TravelGroup;
import multi.second.project.domain.group.repository.TravelGroupRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.repository.MemberRepository;

@SpringBootTest
public class TravleGroupServiceTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TravelGroupRepository travelGroupRepository;
	
	@Test
	public void testCreateTravelGroup() {
		
		Member group1A = memberRepository.findById("group1A").get();
		Member group1B = memberRepository.findById("group1B").get();
		Member group2A = memberRepository.findById("group2A").get();
		Member group2B = memberRepository.findById("group2B").get();
		
		TravelGroup group1 = new TravelGroup();
		TravelGroup group2 = new TravelGroup();
//		TravelGroup group3 = new TravelGroup();
		
//		group1.addMembers(group1A);
//		group1.addMembers(group1B);
//		
//		group2.addMembers(group2A);
//		group2.addMembers(group2B);
		
//		group3.addMembers(group1A);
		
		travelGroupRepository.save(group1);
		travelGroupRepository.save(group2);
//		travelGroupRepository.save(group3);
		
	}
	
	
	@Test
	public void testRemoveTravelGroup() {
		
		Member group1A = memberRepository.findById("group1A").get();
		Member group1B = memberRepository.findById("group1B").get();
		Member group2A = memberRepository.findById("group2A").get();
		Member group2B = memberRepository.findById("group2B").get();
		
		TravelGroup travelGroup = travelGroupRepository.findById(1L).get();
		
//		travelGroup.removeMembers(group1A);
//		group3.addMembers(group1A);
		
		travelGroupRepository.save(travelGroup);
//		travelGroupRepository.save(group3);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
