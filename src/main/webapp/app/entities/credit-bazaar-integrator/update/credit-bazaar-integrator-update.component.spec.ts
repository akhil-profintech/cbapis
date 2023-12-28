import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CreditBazaarIntegratorService } from '../service/credit-bazaar-integrator.service';
import { ICreditBazaarIntegrator } from '../credit-bazaar-integrator.model';
import { CreditBazaarIntegratorFormService } from './credit-bazaar-integrator-form.service';

import { CreditBazaarIntegratorUpdateComponent } from './credit-bazaar-integrator-update.component';

describe('CreditBazaarIntegrator Management Update Component', () => {
  let comp: CreditBazaarIntegratorUpdateComponent;
  let fixture: ComponentFixture<CreditBazaarIntegratorUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let creditBazaarIntegratorFormService: CreditBazaarIntegratorFormService;
  let creditBazaarIntegratorService: CreditBazaarIntegratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CreditBazaarIntegratorUpdateComponent],
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
      .overrideTemplate(CreditBazaarIntegratorUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CreditBazaarIntegratorUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    creditBazaarIntegratorFormService = TestBed.inject(CreditBazaarIntegratorFormService);
    creditBazaarIntegratorService = TestBed.inject(CreditBazaarIntegratorService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const creditBazaarIntegrator: ICreditBazaarIntegrator = { id: 456 };

      activatedRoute.data = of({ creditBazaarIntegrator });
      comp.ngOnInit();

      expect(comp.creditBazaarIntegrator).toEqual(creditBazaarIntegrator);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreditBazaarIntegrator>>();
      const creditBazaarIntegrator = { id: 123 };
      jest.spyOn(creditBazaarIntegratorFormService, 'getCreditBazaarIntegrator').mockReturnValue(creditBazaarIntegrator);
      jest.spyOn(creditBazaarIntegratorService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creditBazaarIntegrator });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: creditBazaarIntegrator }));
      saveSubject.complete();

      // THEN
      expect(creditBazaarIntegratorFormService.getCreditBazaarIntegrator).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(creditBazaarIntegratorService.update).toHaveBeenCalledWith(expect.objectContaining(creditBazaarIntegrator));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreditBazaarIntegrator>>();
      const creditBazaarIntegrator = { id: 123 };
      jest.spyOn(creditBazaarIntegratorFormService, 'getCreditBazaarIntegrator').mockReturnValue({ id: null });
      jest.spyOn(creditBazaarIntegratorService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creditBazaarIntegrator: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: creditBazaarIntegrator }));
      saveSubject.complete();

      // THEN
      expect(creditBazaarIntegratorFormService.getCreditBazaarIntegrator).toHaveBeenCalled();
      expect(creditBazaarIntegratorService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICreditBazaarIntegrator>>();
      const creditBazaarIntegrator = { id: 123 };
      jest.spyOn(creditBazaarIntegratorService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ creditBazaarIntegrator });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(creditBazaarIntegratorService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
