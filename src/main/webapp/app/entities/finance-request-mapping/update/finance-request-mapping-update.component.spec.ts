import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FinanceRequestMappingService } from '../service/finance-request-mapping.service';
import { IFinanceRequestMapping } from '../finance-request-mapping.model';
import { FinanceRequestMappingFormService } from './finance-request-mapping-form.service';

import { FinanceRequestMappingUpdateComponent } from './finance-request-mapping-update.component';

describe('FinanceRequestMapping Management Update Component', () => {
  let comp: FinanceRequestMappingUpdateComponent;
  let fixture: ComponentFixture<FinanceRequestMappingUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let financeRequestMappingFormService: FinanceRequestMappingFormService;
  let financeRequestMappingService: FinanceRequestMappingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FinanceRequestMappingUpdateComponent],
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
      .overrideTemplate(FinanceRequestMappingUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FinanceRequestMappingUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    financeRequestMappingFormService = TestBed.inject(FinanceRequestMappingFormService);
    financeRequestMappingService = TestBed.inject(FinanceRequestMappingService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const financeRequestMapping: IFinanceRequestMapping = { id: 456 };

      activatedRoute.data = of({ financeRequestMapping });
      comp.ngOnInit();

      expect(comp.financeRequestMapping).toEqual(financeRequestMapping);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinanceRequestMapping>>();
      const financeRequestMapping = { id: 123 };
      jest.spyOn(financeRequestMappingFormService, 'getFinanceRequestMapping').mockReturnValue(financeRequestMapping);
      jest.spyOn(financeRequestMappingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financeRequestMapping });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: financeRequestMapping }));
      saveSubject.complete();

      // THEN
      expect(financeRequestMappingFormService.getFinanceRequestMapping).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(financeRequestMappingService.update).toHaveBeenCalledWith(expect.objectContaining(financeRequestMapping));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinanceRequestMapping>>();
      const financeRequestMapping = { id: 123 };
      jest.spyOn(financeRequestMappingFormService, 'getFinanceRequestMapping').mockReturnValue({ id: null });
      jest.spyOn(financeRequestMappingService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financeRequestMapping: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: financeRequestMapping }));
      saveSubject.complete();

      // THEN
      expect(financeRequestMappingFormService.getFinanceRequestMapping).toHaveBeenCalled();
      expect(financeRequestMappingService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFinanceRequestMapping>>();
      const financeRequestMapping = { id: 123 };
      jest.spyOn(financeRequestMappingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ financeRequestMapping });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(financeRequestMappingService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
