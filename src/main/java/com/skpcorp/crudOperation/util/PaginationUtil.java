package com.skpcorp.crudOperation.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.skpcorp.crudOperation.model.PageListModel;
import com.skpcorp.crudOperation.model.PageModel;

@Component
public class PaginationUtil {


	public static PageListModel getPageList(final Page<?> page) {
		if (page.isEmpty()) {
			return null;
		}

		final PageListModel pageList = new PageListModel();
		final List<PageModel> pages = new ArrayList<>();
		pages.add(getFirst(page));
		pages.add(getPrevious(page));
		pages.addAll(getPages(page));
		pages.add(getNext(page));
		pages.add(getLast(page));

		pageList.setPages(pages);

		final long start = (page.getNumber() * page.getSize()) + 1l;

		pageList.setStart((page.getNumber() * page.getSize()) + 1l);
		pageList.setEnd(Math.min(start + page.getSize() - 1, page.getTotalElements()));
		pageList.setTotalItems(page.getTotalElements());
		pageList.setTotalPages(page.getTotalPages());
		pageList.setSize(page.getSize());
		return pageList;

	}

	private static PageModel getFirst(final Page<?> page) {
		return new PageModel().setDisabled(!page.hasPrevious()).setName("<<").setPath(getPagePath(0, page.getSize()));
	}

	private static PageModel getPrevious(final Page<?> page) {
		return new PageModel().setDisabled(!page.hasPrevious()).setName("Previous")
				.setPath(getPagePath(page.previousOrFirstPageable().getPageNumber(), page.getSize()));
	}

	private static PageModel getNext(final Page<?> page) {
		return new PageModel().setDisabled(!page.hasNext()).setName("Next")
				.setPath(getPagePath(page.nextOrLastPageable().getPageNumber(), page.getSize()));
	}

	private static PageModel getLast(final Page<?> page) {
		return new PageModel().setDisabled(!page.hasNext()).setName(">>")
				.setPath(getPagePath(page.getTotalPages() - 1, page.getSize()));
	}

	private static String getPagePath(final int pageNumber, final int paeSize) {
		return "?page=" + pageNumber + "&size=" + paeSize;
	}

	private static List<PageModel> getPages(final Page<?> page) {
		final List<PageModel> pages = new ArrayList<>();
		final int start = Math.max(0, Math.min(page.getNumber() - 3, page.getTotalPages() - 6));
		final int end = Math.min(start + 6, page.getTotalPages());
		for (int i = start; i < end; i++) {
			PageModel pm = new PageModel().setActive(i == page.getNumber()).setName((i + 1) + "")
					.setPath(getPagePath(i, page.getSize()));
			pages.add(pm);
		}
		return pages;
	}
}
