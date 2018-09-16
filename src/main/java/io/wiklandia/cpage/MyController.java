package io.wiklandia.cpage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MyController {

	private final PersonRepository personRepository;
	private static List<Person> persons = new ArrayList<>();
	static {
		for (int i = 0; i < 1234; i++) {
			persons.add(Person.builder().firstName(String.format("%03d", i)).build());
		}
	}

	@GetMapping("cool")
	public String cool(ModelMap model, @SortDefault("firstName") Pageable pageable) {
		model.addAttribute("page", personRepository.findAll(pageable));
		return "cool";
	}

	@GetMapping("own")
	public String own(ModelMap model, Pageable pageable) {

		int offset = (int) pageable.getOffset();
		model.addAttribute("page",
				new PageImpl<>(persons.subList(offset, Math.min(offset + pageable.getPageSize(), persons.size())),
						pageable, persons.size()));
		return "own";
	}

}
