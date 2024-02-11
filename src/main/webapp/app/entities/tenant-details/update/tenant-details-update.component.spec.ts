import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TenantDetailsService } from '../service/tenant-details.service';
import { ITenantDetails } from '../tenant-details.model';
import { TenantDetailsFormService } from './tenant-details-form.service';

import { TenantDetailsUpdateComponent } from './tenant-details-update.component';

describe('TenantDetails Management Update Component', () => {
  let comp: TenantDetailsUpdateComponent;
  let fixture: ComponentFixture<TenantDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tenantDetailsFormService: TenantDetailsFormService;
  let tenantDetailsService: TenantDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), TenantDetailsUpdateComponent],
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
      .overrideTemplate(TenantDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TenantDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tenantDetailsFormService = TestBed.inject(TenantDetailsFormService);
    tenantDetailsService = TestBed.inject(TenantDetailsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const tenantDetails: ITenantDetails = { id: 456 };

      activatedRoute.data = of({ tenantDetails });
      comp.ngOnInit();

      expect(comp.tenantDetails).toEqual(tenantDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITenantDetails>>();
      const tenantDetails = { id: 123 };
      jest.spyOn(tenantDetailsFormService, 'getTenantDetails').mockReturnValue(tenantDetails);
      jest.spyOn(tenantDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tenantDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tenantDetails }));
      saveSubject.complete();

      // THEN
      expect(tenantDetailsFormService.getTenantDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(tenantDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(tenantDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITenantDetails>>();
      const tenantDetails = { id: 123 };
      jest.spyOn(tenantDetailsFormService, 'getTenantDetails').mockReturnValue({ id: null });
      jest.spyOn(tenantDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tenantDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tenantDetails }));
      saveSubject.complete();

      // THEN
      expect(tenantDetailsFormService.getTenantDetails).toHaveBeenCalled();
      expect(tenantDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITenantDetails>>();
      const tenantDetails = { id: 123 };
      jest.spyOn(tenantDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tenantDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tenantDetailsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
