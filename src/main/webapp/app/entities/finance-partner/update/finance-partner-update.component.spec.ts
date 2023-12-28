import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FinancePartnerService } from '../service/finance-partner.service';
import { IFinancePartner } from '../finance-partner.model';
import { FinancePartnerFormService } from './finance-partner-form.service';

import { FinancePartnerUpdateComponent } from './finance-partner-update.component';

describe('FinancePartner Management Update Component', () => {
  let comp: FinancePartnerUpdateComponent;
  let fixture: ComponentFixture<FinancePartnerUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let financePartnerFormService: FinancePartnerFormService;
  let financePartnerService: FinancePartnerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FinancePartnerUpdateComponent],
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
      .overrideTemplate(FinancePartnerUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FinancePartnerUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    financePartnerFormService = TestBed.inject(FinancePartnerFormService);
    financePartnerService = TestBed.inject(FinancePartnerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const financePartner: IFinancePartner = { id: 456 };

      activatedRoute.data = of({ financePartner });
      comp.ngOnInit();

      expect(comp.financePartner).toEqual(financePartner);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinancePartner>>();
      const financePartner = { id: 123 };
      jest.spyOn(financePartnerFormService, 'getFinancePartner').mockReturnValue(financePartner);
      jest.spyOn(financePartnerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financePartner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: financePartner }));
      saveSubject.complete();

      // THEN
      expect(financePartnerFormService.getFinancePartner).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(financePartnerService.update).toHaveBeenCalledWith(expect.objectContaining(financePartner));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinancePartner>>();
      const financePartner = { id: 123 };
      jest.spyOn(financePartnerFormService, 'getFinancePartner').mockReturnValue({ id: null });
      jest.spyOn(financePartnerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financePartner: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: financePartner }));
      saveSubject.complete();

      // THEN
      expect(financePartnerFormService.getFinancePartner).toHaveBeenCalled();
      expect(financePartnerService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinancePartner>>();
      const financePartner = { id: 123 };
      jest.spyOn(financePartnerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financePartner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(financePartnerService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
