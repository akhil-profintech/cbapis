jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { UpdateVAService } from '../service/update-va.service';

import { UpdateVADeleteDialogComponent } from './update-va-delete-dialog.component';

describe('UpdateVA Management Delete Component', () => {
  let comp: UpdateVADeleteDialogComponent;
  let fixture: ComponentFixture<UpdateVADeleteDialogComponent>;
  let service: UpdateVAService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, UpdateVADeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(UpdateVADeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(UpdateVADeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(UpdateVAService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
