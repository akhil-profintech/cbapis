import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FinanceRequestMappingDetailComponent } from './finance-request-mapping-detail.component';

describe('FinanceRequestMapping Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinanceRequestMappingDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FinanceRequestMappingDetailComponent,
              resolve: { financeRequestMapping: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FinanceRequestMappingDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load financeRequestMapping on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FinanceRequestMappingDetailComponent);

      // THEN
      expect(instance.financeRequestMapping).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
