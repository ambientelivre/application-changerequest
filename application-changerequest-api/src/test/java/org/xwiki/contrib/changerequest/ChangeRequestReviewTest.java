/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.changerequest;

import org.junit.jupiter.api.Test;
import org.xwiki.user.UserReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link ChangeRequestReview}.
 *
 * @version $Id$
 * @since 0.14
 */
class ChangeRequestReviewTest
{
    @Test
    void cloneWithChangeRequest()
    {
        ChangeRequest originalChangeRequest = mock(ChangeRequest.class, "originalCR");
        UserReference author = mock(UserReference.class, "author");
        ChangeRequestReview review = new ChangeRequestReview(originalChangeRequest, true, author);
        review
            .setSaved(true)
            .setNew(false);

        ChangeRequest newChangeRequest = mock(ChangeRequest.class, "otherCR");
        ChangeRequestReview clonedReview = review.cloneWithChangeRequest(newChangeRequest);
        assertNotEquals(clonedReview, review);
        assertTrue(clonedReview.isNew());
        assertTrue(clonedReview.isValid());
        assertTrue(clonedReview.isApproved());
        assertFalse(clonedReview.isSaved());

        assertEquals(clonedReview.getAuthor(), review.getAuthor());
        assertEquals(clonedReview.getReviewDate(), review.getReviewDate());
    }
}