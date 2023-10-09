package refactoring_guru.iterator.example.iterators;

import refactoring_guru.iterator.example.profile.Profile;
import refactoring_guru.iterator.example.social_networks.LinkedIn;

import java.util.ArrayList;
import java.util.List;

public class LinkedInIterator implements ProfileIterator {
  private final LinkedIn linkedIn;
  private final String type;
  private final String email;
  private int currentPosition = 0;
  private final List<String> emails = new ArrayList<>();
  private final List<Profile> contacts = new ArrayList<>();

  public LinkedInIterator(LinkedIn linkedIn, String type, String email) {
    this.linkedIn = linkedIn;
    this.type = type;
    this.email = email;
  }

  @Override
  public boolean hasNext() {
    lazyLoad();
    return currentPosition < emails.size();
  }

  @Override
  public Profile getNext() {
    if (!hasNext()) {
      throw new IllegalArgumentException("Next is null ");
    }

    String friendEmail = emails.get(currentPosition);
    Profile friendContact = contacts.get(currentPosition);
    if (friendContact == null) {
      friendContact = linkedIn.requestContactInfoFromLinkedInAPI(friendEmail);
      contacts.set(currentPosition, friendContact);
    }
    currentPosition++;
    return friendContact;
  }

  @Override
  public void reset() {
    currentPosition = 0;
  }

  private void lazyLoad() {
    if (emails.isEmpty()) {
      List<String> profiles = linkedIn.requestRelatedContactsFromLinkedInAPI(this.email, this.type);
      for (String profile : profiles) {
        this.emails.add(profile);
        this.contacts.add(null);
      }
    }
  }
}
