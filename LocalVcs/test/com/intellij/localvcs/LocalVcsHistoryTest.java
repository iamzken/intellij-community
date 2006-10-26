package com.intellij.localvcs;

import java.util.List;

import org.junit.Test;

public class LocalVcsHistoryTest extends LocalVcsTestCase {
  @Test
  public void testRevertingToPreviousVersion() {
    myVcs.addFile("file", "");
    myVcs.commit();
    assertTrue(myVcs.hasFile("file"));

    myVcs.revert();
    assertFalse(myVcs.hasFile("file"));
  }

  @Test
  public void testRevertingClearsAllPendingChanges() {
    myVcs.addFile("file1", "");
    myVcs.commit();

    myVcs.addFile("file2", "");
    assertFalse(myVcs.isClean());

    myVcs.revert();
    assertTrue(myVcs.isClean());
  }

  @Test
  public void testRevertingWhenNoPreviousVersions() {
    try {
      myVcs.revert();
      myVcs.revert();
    } catch (Exception e) {
      fail(e.toString());
    }
  }

  @Test
  public void testClearingChangesAfterRevertWhenNoPreviousVersions() {
    myVcs.addFile("file", "");
    assertFalse(myVcs.isClean());

    myVcs.revert();
    assertTrue(myVcs.isClean());
  }

  @Test
  public void testGettingSnapshots() {
    myVcs.addFile("file1", "content1");
    myVcs.addFile("file2", "content2");
    myVcs.commit();

    myVcs.addFile("file3", "content3");
    myVcs.changeFile("file1", "new content1");
    myVcs.commit();

    Integer id1 = myVcs.getFileRevision("file1").getObjectId();
    Integer id2 = myVcs.getFileRevision("file2").getObjectId();
    Integer id3 = myVcs.getFileRevision("file3").getObjectId();

    List<Snapshot> snapshots = myVcs.getSnapshots();
    assertEquals(2, snapshots.size());

    assertElements(
        new Object[]{
            new Revision(id1, "file1", "new content1"),
            new Revision(id2, "file2", "content2"),
            new Revision(id3, "file3", "content3")},
        snapshots.get(0).getRevisions());

    assertElements(
        new Object[]{
            new Revision(id1, "file1", "content1"),
            new Revision(id2, "file2", "content2")},
        snapshots.get(1).getRevisions());
  }

  @Test
  public void testGettingSnapshotsOnCleanVcs() {
    assertTrue(myVcs.getSnapshots().isEmpty());
  }

  @Test
  public void testGettingLabeledSnapshot() {
    myVcs.addFile("file", "content");
    myVcs.commit();

    myVcs.putLabel("label");

    myVcs.changeFile("file", "new content");
    myVcs.commit();

    Snapshot s = myVcs.getSnapshot("label");
    assertNotNull(s);
    assertRevisionContent("content", s.getFileRevision("file"));
  }

  @Test
  public void testGettingSnapshotWithUnknownLabel() {
    myVcs.addFile("file", "content");
    myVcs.commit();
    myVcs.putLabel("label");

    assertNull(myVcs.getSnapshot("unknown label"));
  }
}
