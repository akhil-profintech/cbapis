import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { EscrowAccountDetailsService } from '../service/escrow-account-details.service';
import { IEscrowAccountDetails } from '../escrow-account-details.model';
import { EscrowAccountDetailsFormService } from './escrow-account-details-form.service';

import { EscrowAccountDetailsUpdateComponent } from './escrow-account-details-update.component';

describe('EscrowAccountDetails Management Update Component', () => {
  let comp: EscrowAccountDetailsUpdateComponent;
  let fixture: ComponentFixture<EscrowAccountDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let escrowAccountDetailsFormService: EscrowAccountDetailsFormService;
  let escrowAccountDetailsService: EscrowAccountDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), EscrowAccountDetailsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(EscrowAccountDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EscrowAccountDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    escrowAccountDetailsFormService = TestBed.inject(EscrowAccountDetailsFormService);
    escrowAccountDetailsService = TestBed.inject(EscrowAccountDetailsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const escrowAccountDetails: IEscrowAccountDetails = { id: 456 };

      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      expect(comp.escrowAccountDetails).toEqual(escrowAccountDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowAccountDetails>>();
      const escrowAccountDetails = { id: 123 };
      jest.spyOn(escrowAccountDetailsFormService, 'getEscrowAccountDetails').mockReturnValue(escrowAccountDetails);
      jest.spyOn(escrowAccountDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: escrowAccountDetails }));
      saveSubject.complete();

      // THEN
      expect(escrowAccountDetailsFormService.getEscrowAccountDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(escrowAccountDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(escrowAccountDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowAccountDetails>>();
      const escrowAccountDetails = { id: 123 };
      jest.spyOn(escrowAccountDetailsFormService, 'getEscrowAccountDetails').mockReturnValue({ id: null });
      jest.spyOn(escrowAccountDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowAccountDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: escrowAccountDetails }));
      saveSubject.complete();

      // THEN
      expect(escrowAccountDetailsFormService.getEscrowAccountDetails).toHaveBeenCalled();
      expect(escrowAccountDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEscrowAccountDetails>>();
      const escrowAccountDetails = { id: 123 };
      jest.spyOn(escrowAccountDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ escrowAccountDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(escrowAccountDetailsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
